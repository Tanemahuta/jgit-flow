package com.atlassian.maven.plugins.jgitflow;

import java.io.File;

import com.atlassian.jgitflow.core.InitContext;

import com.google.common.base.Strings;

/**
 * @since version
 */
public class ReleaseContext
{
    private boolean allowSnapshots;
    private boolean interactive;
    private boolean autoVersionSubmodules;
    private boolean updateDependencies;
    private boolean push;
    private boolean keepBranch;
    private boolean squash;
    private boolean noTag;
    private boolean noDeploy;
    private boolean noBuild;
    private boolean featureRebase;
    private boolean useReleaseProfile;
    private boolean enableFeatureVersions;
    private String args;
    private String tagMessage;
    private String defaultReleaseVersion;
    private String defaultDevelopmentVersion;
    private String defaultFeatureName;
    private String releaseBranchVersionSuffix;
    private InitContext flowInitContext;
    private final File baseDir;
    
    public ReleaseContext(File baseDir)
    {
        this.baseDir = baseDir;
        this.allowSnapshots = false;
        this.defaultReleaseVersion = null;
        this.defaultDevelopmentVersion = null;
        this.interactive = true;
        this.autoVersionSubmodules = false;
        this.updateDependencies = true;
        this.push = true;
        this.keepBranch = false;
        this.squash = false;
        this.noTag = false;
        this.noDeploy = false;
        this.noBuild = false;
        this.featureRebase = false;
        this.useReleaseProfile = true;
        this.args = "";
        this.releaseBranchVersionSuffix = "release";
        this.enableFeatureVersions = true;
        this.tagMessage = "tagging release ${version}";
        this.flowInitContext = new InitContext();
    }

    public boolean isAllowSnapshots()
    {
        return allowSnapshots;
    }

    public ReleaseContext setAllowSnapshots(boolean allowSnapshots)
    {
        this.allowSnapshots = allowSnapshots;
        return this;
    }

    public String getDefaultReleaseVersion()
    {
        return defaultReleaseVersion;
    }

    public ReleaseContext setDefaultReleaseVersion(String defaultReleaseVersion)
    {
        this.defaultReleaseVersion = defaultReleaseVersion;
        return this;
    }

    public String getDefaultDevelopmentVersion()
    {
        return defaultDevelopmentVersion;
    }

    public ReleaseContext setDefaultDevelopmentVersion(String version)
    {
        this.defaultDevelopmentVersion = version;
        return this;
    }

    public boolean isInteractive()
    {
        return interactive;
    }

    public ReleaseContext setInteractive(boolean interactive)
    {
        this.interactive = interactive;
        return this;
    }

    public boolean isAutoVersionSubmodules()
    {
        return autoVersionSubmodules;
    }

    public ReleaseContext setAutoVersionSubmodules(boolean autoVersionSubmodules)
    {
        this.autoVersionSubmodules = autoVersionSubmodules;
        return this;
    }

    public InitContext getFlowInitContext()
    {
        return flowInitContext;
    }

    public ReleaseContext setFlowInitContext(InitContext flowInitContext)
    {
        this.flowInitContext = flowInitContext;
        return this;
    }

    public boolean isUpdateDependencies()
    {
        return updateDependencies;
    }

    public ReleaseContext setUpdateDependencies(boolean updateDependencies)
    {
        this.updateDependencies = updateDependencies;
        return this;
    }

    public File getBaseDir()
    {
        return baseDir;
    }

    public boolean isPush()
    {
        return push;
    }

    public ReleaseContext setPush(boolean push)
    {
        this.push = push;
        return this;
    }

    public boolean isKeepBranch()
    {
        return keepBranch;
    }

    public ReleaseContext setKeepBranch(boolean keepBranch)
    {
        this.keepBranch = keepBranch;
        return this;
    }

    public boolean isSquash()
    {
        return squash;
    }

    public ReleaseContext setSquash(boolean squash)
    {
        this.squash = squash;
        return this;
    }

    public boolean isNoTag()
    {
        return noTag;
    }

    public ReleaseContext setNoTag(boolean noTag)
    {
        this.noTag = noTag;
        return this;
    }

    public boolean isNoDeploy()
    {
        return noDeploy;
    }

    public ReleaseContext setNoDeploy(boolean deploy)
    {
        this.noDeploy = deploy;
        return this;
    }

    public boolean isNoBuild()
    {
        return noBuild;
    }

    /*
     * NOTE: This should only be used for testing!!!
     */
    public ReleaseContext setNoBuild(boolean nobuild)
    {
        this.noBuild = nobuild;
        return this;
    }

    public boolean isFeatureRebase()
    {
        return featureRebase;
    }

    public ReleaseContext setFeatureRebase(boolean rebase)
    {
        this.featureRebase = rebase;
        return this;
    }

    public String getTagMessage()
    {
        return tagMessage;
    }

    public ReleaseContext setTagMessage(String msg)
    {
        if(!Strings.isNullOrEmpty(msg))
        {
            this.tagMessage = msg;
        }
        
        return this;
    }

    public boolean isUseReleaseProfile()
    {
        return useReleaseProfile;
    }

    public ReleaseContext setUseReleaseProfile(boolean useReleaseProfile)
    {
        this.useReleaseProfile = useReleaseProfile;
        return this;
    }

    public String getArgs()
    {
        return args;
    }

    public ReleaseContext setArgs(String args)
    {
        this.args = args;
        return this;
    }

    public ReleaseContext setDefaultFeatureName(String defaultFeatureName)
    {
        this.defaultFeatureName = defaultFeatureName;
        return this;
    }
    
    public String getDefaultFeatureName()
    {
        return defaultFeatureName;
    }

    public ReleaseContext setReleaseBranchVersionSuffix(String suffix)
    {
        this.releaseBranchVersionSuffix = suffix;
        return this;
    }

    public String getReleaseBranchVersionSuffix()
    {
        return releaseBranchVersionSuffix;
    }

    public boolean isEnableFeatureVersions()
    {
        return enableFeatureVersions;
    }

    public ReleaseContext setEnableFeatureVersions(boolean enable)
    {
        this.enableFeatureVersions = enable;
        return this;
    }
}
