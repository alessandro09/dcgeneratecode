package com.datacoper.maven.mojos;

import br.com.datacoper.maven.mojos.MojoConstants;
import com.datacoper.maven.enums.properties.EnumDCProjectType;
import com.datacoper.maven.exception.DcRuntimeException;
import com.datacoper.maven.util.ClassLoaderUtil;
import com.datacoper.maven.util.ConsoleUtil;
import com.datacoper.maven.util.DCProjectUtil;
import com.datacoper.maven.util.LogUtil;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

public abstract class AbstractDCMojo extends AbstractMojo {

    @Parameter(property = "project", required = true, readonly = true)
    protected MavenProject _project;

    @Parameter(property = "class", defaultValue = MojoConstants.PROPERTY_NOT_INFORMED)
    protected String _completeEntityName;

    public AbstractDCMojo() {
        super();
        LogUtil.setLog(getLog());
    }
    
    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        try {
            validate();
        } catch (Throwable e) {
            LogUtil.error(e.getMessage());
            return;
        }
        
        try {
            LogUtil.info("\n\nStart plugin {0}", getMojoName());
            
            important();
            
            ClassLoaderUtil.loadClassLoader(_project);

            init();
        } catch(Throwable e) {
            LogUtil.error(e);
        }
    }

    private void important() {
        ConsoleUtil.sysOutl("\n********************************************************************************************************************\n\n");
    }
    
    public void validate() {
        if (isValidateTypeProjectToRun()) validateTypeProjectForExecution();
    }

    public void validateTypeProjectForExecution() {
        if (!DCProjectUtil.isProjectType(EnumDCProjectType.PARENT, _project)) {
            throw new DcRuntimeException("SKIP - the plugin can only be run from a parent project");
        }
    }

    public abstract void init();
    
    public abstract String getMojoName();
    
    public boolean isValidateTypeProjectToRun() {
        return false;
    }
}
