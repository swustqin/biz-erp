package com.business.erp.config;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.resource.ResourceUrlProvider;

import javax.annotation.Resource;

@ControllerAdvice
public class ResourceMappingConfig {

    @Resource
    private ResourceUrlProvider resourceUrlProvider;

    /**
     * Version manager
     * <p>
     * 在使用第三方库，我们可以是使用WebJarAssetLocator的方式进行版本管理，
     * 但是使用自己写css和js，建议使用ResourceUrlProvider进行版本管理，
     * 并避免在版本发生改变时，由于浏览器缓存而产生资源版本未改变的错误.
     * <p>
     * <p>
     * 前端页面上，我们这么引入:<script type="text/javascript" src="${request.contextPath }/${urls.getForLookupPath('/js/index.js')}"></script>
     * 实际会产生md5后缀:如:js/index-a789359d91ae435bc45489c5e6978b34.js
     * 这样客户端的资源路径就发生改变，回去服务器重新获取。
     *
     * @return ResourceUrlProvider
     */
    @ModelAttribute("urls")
    public ResourceUrlProvider urls() {
        return this.resourceUrlProvider;
    }


}
