package cf.study.ex.reflects;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.jar.Attributes;
import java.util.jar.Attributes.Name;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.Manifest;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.SystemUtils;
import org.apache.log4j.Logger;
import org.apache.xbean.classloader.JarFileClassLoader;
import org.junit.Test;

import misc.MiscUtils;
import misc.MiscUtils.TraverseMatcher;

public class Reflects {

	public static Class<?> preloadClazzByName(String name) {
		if (StringUtils.isBlank(name)) return null;
		try {
			return Class.forName(name, false, ClassLoader.getSystemClassLoader());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static List<Class<?>> extractClazz(final File f) {
		List<Class<?>> clzList = new LinkedList<Class<?>>();
		Consumer<JarEntry> visitor = (JarEntry je) -> {
			String enName = je.getName();
			
			if (StringUtils.endsWith(enName, "/")) {
				enName = StringUtils.removeEnd(enName, "/").replace('/', '.');
				return;
			}
			
			if (StringUtils.endsWith(enName, ".class")) {
				enName = enName.replace('/', '.');
				clzList.add(preloadClazzByName(StringUtils.substringBeforeLast(enName, ".class")));
			}
		};
		
		Predicate<JarEntry> filter = (je) -> {
			return !(je.isDirectory() || je.getName().contains("META-INF") || je.getName().endsWith("package-info"));
		};
		
		try {
			extractJarStructure(f, filter, visitor);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return clzList;
	}
	
	public static void extractJarStructure(final File f, final Predicate<JarEntry> filter, final Consumer<JarEntry> visitor) throws MalformedURLException, IOException {
		if (f == null || !f.exists() || !f.isFile() || !f.canRead()) {
			return;
		}
		
		try (JarFileClassLoader cl = new JarFileClassLoader("jfcl", new URL[] { f.toURI().toURL() }, ClassLoader.getSystemClassLoader()); 
			JarFile jf = new JarFile(f); 
			Stream<JarEntry> entryStream = jf.stream()) {
			
			System.out.println(String.format("entries: %d", jf.size()));
			Stream<JarEntry> _stream = entryStream;
			if (filter != null) {
				_stream = _stream.filter(filter);
			}
			if (visitor != null) {
				_stream.forEach(visitor);
			}
		}
	}
	
	public static List<Class<?>> loadClzzFromJar(File jarFile, ClassLoader defaultLoader) throws Exception {
		List<Class<?>> clsList = new LinkedList<Class<?>>();
		if (jarFile == null || !jarFile.exists() || !jarFile.isFile() || !jarFile.canRead()) {
			return clsList;
		}

		JarFileClassLoader cl = new JarFileClassLoader("jfcl", new URL[] { jarFile.toURI().toURL() }, defaultLoader);

		try {
			try (JarFile jf = new JarFile(jarFile); 
				Stream<JarEntry> entryStream = jf.stream()) {
				
				System.out.println(String.format("entries: %d", jf.size()));
				entryStream.filter((je) ->!je.isDirectory())
				.map((je) -> {
					String clsName = StringUtils.removeEnd(je.getName().replace('/', '.'), ".class");
					try {
						return cl.loadClass(clsName);
					} catch (Exception e) {
						System.err.println(clsName + ": " + e.getMessage());
					}
					return null;
				}).forEach((cls) -> {
					if (cls != null) {
						clsList.add(cls);
					}
				});

			}
		} finally {
			cl.close();
		}

		return clsList;
	}
	
	public static File getJarFileInClassPath(String libName) {
		if (StringUtils.isBlank(libName))
			return null;

		String[] classPaths = StringUtils.split(SystemUtils.JAVA_CLASS_PATH, ';');
		Optional<String> opt = Stream.of(classPaths).filter((String str) -> str.contains(libName)).findFirst();
		return opt.isPresent() ? new File(opt.get()) : null;
	}

	@SuppressWarnings("unchecked")
	public static final Map<String, Class<?>> primitives = MiscUtils.map(
				"void", void.class,
				"long", long.class,
				"int", int.class,
				"char", char.class,
				"boolean", boolean.class,
				"byte", byte.class,
				"double", double.class,
				"short", short.class,
				"float", float.class
			);
	private static final Logger log = Logger.getLogger(Reflects.class);

	public static String checkClzName(Class<?> clz) {
		if (clz == null) return StringUtils.EMPTY;
		String clzName = clz.getName();
		
		if (primitives.containsKey(clzName)) {
			clz = primitives.get(clzName);
			clzName = clz.getName();
		}
		return clzName;
	}

	public static boolean isCycleAnnotated(Class<?> ac1, Class<?> ac2) {
		if (!(ac1 != null && ac1.isAnnotation() && ac2 != null && ac2.isAnnotation()))
			return false;
		
		if (ac1 == ac2) {
			return true;
		}
		
		Class<?>[] acs1 = Stream.of(ac1.getAnnotations()).map(Annotation::annotationType).toArray(Class<?>[]::new);
		Class<?>[] acs2 = Stream.of(ac2.getAnnotations()).map(Annotation::annotationType).toArray(Class<?>[]::new);
		
		if (ArrayUtils.contains(acs1, ac2)
			&& ArrayUtils.contains(acs2, ac1))
			return true;
		
//		Stream.of(ac1.getAnnotations()).map(Annotation::annotationType).spliterator().
		
		TraverseMatcher<Class<?>> tc1 = new TraverseMatcher<Class<?>>();
		tc1.getChildren = (c)->Stream.of(c.getAnnotations()).map(Annotation::annotationType).collect(Collectors.toList());
		tc1.condition = (c)->c == ac2;
		
		return Stream.of(ac2.getAnnotations()).anyMatch(_ac->isCycleAnnotated(ac1, _ac.annotationType()));
	}
	
	public static Class<?> loadClass(final String name) {
		if (primitives.containsKey(name)) {
			return primitives.get(name);
		}
		try {
			return Class.forName(name, false, ClassLoader.getSystemClassLoader());
		} catch (ClassNotFoundException e) {
			log.error("class not found: " + name);
		}
		
		return null;
	}
	
	public static List<JarFile> searchJarFiles(Path path) throws Exception {
		List<JarFile> reList = new LinkedList<>();
		
		if (path == null) return reList;
		
		File _f = path.toFile();
		if (_f.isFile() && "jar".equals(FilenameUtils.getExtension(_f.getName()))) {
			reList.add(new JarFile(_f));
			return reList;
		}
		
		try (Stream<Path> founds = Files.find(path, 
				Integer.MAX_VALUE, 
				(p, a)->p.toFile().getName().matches("^(?!.*?(source|doc)).*jar$")
					&& a.isRegularFile())) {
			founds.map(Path::toFile).map(MiscUtils.wrapException(JarFile::new, null)).filter(jf->jf != null).forEach(reList::add);
		}
		return reList; 
	}
	
	@Test
	public void testSearchJarFiles() throws Exception {
		Path p = Paths.get("E:\\Java\\libs\\maven");
		List<JarFile> jfList = searchJarFiles(p);
		
		for (JarFile jf : jfList) {
			Manifest mf = jf.getManifest();
			Attributes attrs = mf != null ? mf.getMainAttributes() : null;
			System.out.println(jf.size()
					+ "\t" + (attrs != null ? attrs.getValue(Name.IMPLEMENTATION_TITLE) : "\t")
					+ "\t" + jf.getName());
		}
		System.out.println(jfList.size());
		Integer sum = jfList.stream().map(JarFile::size).reduce((a, b)->a+b).get();
		System.out.println(sum);
	}
}
