<#assign className = class.className>
<#assign company = class.company.packag>
<#assign module = class.moduleBasic?lower_case>
package ${class.package};

import javax.ejb.Remote;

import java.util.Optional;
import java.util.List;

import com.datacoper.cooperate.arquitetura.common.beans.BeanConsultaGroup;
import com.datacoper.cooperate.arquitetura.common.beans.PageResult;
import com.datacoper.cooperate.arquitetura.common.interfaces.IRemote;
import com.${company}.cooperate.${module}.common.entities.${class.classNameBasic};
import com.${company}.cooperate.${module}.common.consultas.${class.classNameBasic}VO;
<#list class.imports as import>
import ${import};
</#list>

@Remote
public interface ${className} extends IRemote {
    
    public Optional<${class.classNameBasic}> find(Long id);

    public Optional<${class.classNameBasic}> findFetch(Long id);

    public PageResult<${class.classNameBasic}VO> find(BeanConsultaGroup consultaGroup);

    public List<${class.classNameBasic}> find(List<Long> ids);

    public Long confirm(${class.classNameBasic} ${class.classNameBasic?uncap_first});

    public void excluir(Long id);
}
