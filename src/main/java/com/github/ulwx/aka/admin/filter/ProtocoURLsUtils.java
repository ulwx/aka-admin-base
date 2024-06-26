package com.github.ulwx.aka.admin.filter;

import com.github.ulwx.aka.webmvc.BeanGet;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

public class ProtocoURLsUtils {
    private static volatile Set<String> urls=null;
    public static Set<String> getProtocolPrefex(BeanGet beanGet){
        if(urls!=null) return urls;
        synchronized (ProtocoURLsUtils.class) {
            if(urls==null) {
                urls=new CopyOnWriteArraySet<>();
                Map<String, AcesssNotFilerUrlsProvider> providers
                        = beanGet.beans(AcesssNotFilerUrlsProvider.class);
                if (providers != null && providers.size() > 0) {
                    for (String key : providers.keySet()) {
                        AcesssNotFilerUrlsProvider provider = providers.get(key);
                        List<String> ret = provider.builder();
                        urls.addAll(ret);
                    }
                }
            }
        }
        return urls;
    }

}
