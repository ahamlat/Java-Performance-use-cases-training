package com.ahamlat.javaperformancecourse.memoryleaks.jaxb;



import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.ByteArrayInputStream;

@XmlRootElement
public class ClassloaderLeak
{
    private static final String XML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><classloaderLeak test=\"test\" />";

    @XmlAttribute
    String test;

    public static void main( String[] args ) throws JAXBException, InterruptedException
    {
        System.out.println("start");

        while ( true )
        {
            JAXBContext jc = JAXBContext.newInstance( "com.ahamlat.javaperformancecourse.memoryleaks.jaxb" );
            //JAXBContext jc = JAXBContextLoader.getJAXBContext("com.ahamlat.javaperformancecourse.memoryleaks.jaxb");
            Unmarshaller unmarshaller = jc.createUnmarshaller();
            ClassloaderLeak object = (ClassloaderLeak) unmarshaller.unmarshal( new ByteArrayInputStream( XML.getBytes() ) );
            System.out.println( object.test );
        }
    }
}