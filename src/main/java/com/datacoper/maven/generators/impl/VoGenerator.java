/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.datacoper.maven.generators.impl;

import com.datacoper.maven.metadata.TClass;
import com.datacoper.maven.metadata.builder.TClassBuilder;
import com.datacoper.maven.generators.AbstractGenerator;
import com.datacoper.maven.util.StringUtil;
import org.apache.maven.project.MavenProject;

/**
 *
 * @author alessandro
 */
public class VoGenerator extends AbstractGenerator<TClass> {
    
    public VoGenerator(MavenProject project, TClass data) {
        super(project, "vo", data);
    }

    public String getPackage() {
        return StringUtil.format("com.{0}.cooperate.{1}.common.consultas", data.getCompany().getPackag(), getModuleToPackage());
    }
    
    @Override
    protected TClass prepareForGenerate(TClass clazz) {
        return new TClassBuilder(clazz)
                .withPackag(getPackage())
                .withClassName(data.getClassName().concat("VO"))
                .build();
    }
}
