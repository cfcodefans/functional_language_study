package cf.study.ex.reflects.persistence;

import java.util.jar.Attributes;
import java.util.jar.Attributes.Name;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

import cf.study.ex.reflects.persistence.entity.JarEn; 

public class ReflectsDAO {

	public static JarEn createJarEn(JarFile jf) throws Exception {
		Manifest manifest = jf.getManifest();
		JarEn je = new JarEn();
		je.filename = jf.getName();

		Attributes attrs = manifest.getMainAttributes();

		je.specTitle = attrs.getValue(Name.SPECIFICATION_TITLE);
		je.specVersion = attrs.getValue(Name.SPECIFICATION_VERSION);
		je.specVendor = attrs.getValue(Name.SPECIFICATION_VENDOR);
		je.implTitle = attrs.getValue(Name.IMPLEMENTATION_TITLE);
		je.implVersion = attrs.getValue(Name.IMPLEMENTATION_VERSION);
		je.implVendor = attrs.getValue(Name.IMPLEMENTATION_VENDOR);
		
		return je;
	}
	
}
