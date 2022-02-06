package com.ahamlat.javaperformancecourse.memoryleaks.jaxb;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class JAXBContextLoader
{
    private final Map<String, JAXBContext> contextMap;

    private JAXBContextLoader()
    {
        contextMap = new ConcurrentHashMap<String, JAXBContext>();
    }

    public static JAXBContext getJAXBContext(String contextPath)
    {
        JAXBContextLoader loader = JAXBContextLoaderHolder.instance;

        if (loader.contextMap.containsKey(contextPath))
        {
            return loader.contextMap.get(contextPath);
        }
        else
        {
            try
            {
                JAXBContext jaxbContext = JAXBContext.newInstance(contextPath);
                loader.contextMap.put(contextPath, jaxbContext);
                return jaxbContext;

            } catch (JAXBException e)
            {
                throw new RuntimeException(e);
            }
        }
    }

    private static class JAXBContextLoaderHolder
    {
        private static final JAXBContextLoader instance = new JAXBContextLoader();
    }
}
