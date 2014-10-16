package com.atlassian.maven.plugins.jgitflow.rewrite;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.atlassian.maven.plugins.jgitflow.exception.ProjectRewriteException;

import com.google.common.base.Joiner;
import com.google.common.base.Strings;

import org.apache.maven.artifact.ArtifactUtils;
import org.apache.maven.project.MavenProject;
import org.jdom2.Element;
import org.jdom2.Namespace;

import static com.atlassian.maven.plugins.jgitflow.rewrite.ProjectChangeUtils.getNamespaceOrNull;

/**
 * @since version
 */
public class ProjectReleaseVersionChange implements ProjectChange
{
    private final Map<String, String> releaseVersions;

    private final boolean consistentProjectVersions;
    private final List<String> workLog;

    private ProjectReleaseVersionChange(Map<String, String> releaseVersions, boolean consistentProjectVersions)
    {
        this.releaseVersions = releaseVersions;
        this.consistentProjectVersions = consistentProjectVersions;
        this.workLog = new ArrayList<String>();
    }

    public static ProjectReleaseVersionChange projectReleaseVersionChange(Map<String, String> releaseVersions, boolean consistentProjectVersions)
    {
        return new ProjectReleaseVersionChange(releaseVersions, consistentProjectVersions);
    }

    @Override
    public boolean applyChange(MavenProject project, Element root) throws ProjectRewriteException
    {
        boolean modified = false;

        Namespace ns = getNamespaceOrNull(root);

        Element versionElement = root.getChild("version", ns);
        String projectId = ArtifactUtils.versionlessKey(project.getGroupId(), project.getArtifactId());
        String releaseVersion = releaseVersions.get(projectId);

        if (Strings.isNullOrEmpty(releaseVersion))
        {
            if (consistentProjectVersions && releaseVersions.size() > 0)
            {
                // Use any release version, as the project's versions are consistent/global
                releaseVersion = releaseVersions.values().iterator().next();
            }
            else
            {
                throw new ProjectRewriteException("Release version for " + project.getName() + " was not found");
            }
        }

        if (null == versionElement)
        {
            String parentVersion = null;
            if (project.hasParent())
            {
                MavenProject parent = project.getParent();
                String parentId = ArtifactUtils.versionlessKey(parent.getGroupId(), parent.getArtifactId());

                parentVersion = releaseVersions.get(parentId);
                if (Strings.isNullOrEmpty(parentVersion) && consistentProjectVersions && releaseVersions.size() > 0)
                {
                    // Use any version for the parent, as the project's versions are consistent/global
                    parentVersion = releaseVersions.values().iterator().next();
                }
            }

            if (!releaseVersion.equals(parentVersion))
            {
                Element artifactId = root.getChild("artifactId", ns);
                versionElement = new Element("version");

                workLog.add("setting version to '" + releaseVersion + "'");
                versionElement.setText(releaseVersion);
                root.getChildren().add(root.indexOf(artifactId) + 1, versionElement);
                modified = true;
            }
        }
        else
        {
            workLog.add("updating version '" + versionElement.getTextTrim() + " to '" + releaseVersion + "'");
            versionElement.setText(releaseVersion);
            modified = true;
        }

        return modified;
    }

    @Override
    public String toString()
    {
        if (workLog.isEmpty())
        {
            return "[Update Project Release Version]";
        }
        else
        {
            return "[Update Project Release Version]\n - " + Joiner.on("\n - ").join(workLog);
        }
    }
}
