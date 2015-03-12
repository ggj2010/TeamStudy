package com.team.gaoguangjin.javabase.servlet.ioc.service;

import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import com.team.gaoguangjin.javabase.servlet.ioc.enty.ClassRoom;
import com.team.gaoguangjin.javabase.servlet.ioc.enty.Student;
import com.team.gaoguangjin.javabase.servlet.ioc.enty.Teacher;

public class MainTest extends HttpServlet {
	public static Logger logger = Logger.getLogger(MainTest.class);
	// 方法map
	private static Map<Method, Object> methodMap;
	private static Map<String, String> defalutMap;
	private static boolean debug;
	private static String dateFormat;
	
	static {
		// ApplicationContext context = BeanContextHelper.getApplicationContext();
		// MainTest mainTest = (MainTest) context.getBean("reflact");
		
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			ApplicationContext context = BeanContextHelper.getApplicationContext();
			MainTest mainTest = (MainTest) context.getBean("reflact");
			
			Set<Entry<String, String[]>> set = request.getParameterMap().entrySet();
			Map<String, String> map = new HashMap<String, String>();
			for (Entry<String, String[]> entry : set) {
				if (debug) {
					System.out.println("参数：" + entry.getKey() + "值：" + entry.getValue()[0]);
				}
				map.put(entry.getKey(), entry.getValue()[0]);
			}
			doSaxRequest(map, response);
		} catch (Exception e) {
			logger.error("接收post参数失败" + e.getLocalizedMessage());
		}
		
	}
	
	private void doSaxRequest(Map<String, String> map, HttpServletResponse response) {
		
		try {
			// 初始化需要注解的类
			Student student = new Student();
			Teacher teacher = new Teacher();
			ClassRoom classRoom = new ClassRoom();
			
			// 先复制默认的
			for (Entry<String, String> entryDefault : defalutMap.entrySet()) {
				setValueByKey(entryDefault.getKey(), entryDefault.getValue(), student, teacher, classRoom);
			}
			
			// 先复制默认的
			for (Entry<String, String> entry : map.entrySet()) {
				setValueByKey(entry.getKey(), entry.getValue(), student, teacher, classRoom);
			}
			
			// 这是测试结果
			// System.out.println("xml默认值："+classRoom.toStrings());
			
			response.getWriter().write("xml默认值：" + classRoom.toOutPut());
			response.getWriter().write(
					"前台输入的值：" + student.getStudentName() + "==" + student.getStudentAge() + "==" + teacher.getTeacherName() + "=="
							+ teacher.getTeacherMajor());
			response.getWriter().close();
		} catch (Exception e) {
			logger.error("解析失败" + e.getLocalizedMessage());
		}
		
	}
	
	/**
	 * 根据key和value复制
	 * @param key
	 * @param value
	 * @param object
	 */
	private static void setValueByKey(String key, Object value, Object... object) {
		try {
			// 初始化需要ioc的方法map
			setMethodMap(object);
			// 循环methdodMap
			for (Entry<Method, Object> entryMethod : methodMap.entrySet()) {
				// 获取方法名字和我们传入的参数名字是否一致。如果一致那就赋值
				if (entryMethod.getKey().getName().equalsIgnoreCase("set" + key)) {
					// method.invoke(object,paramtype)
					entryMethod.getKey().invoke(entryMethod.getValue(), ConvertType(value, entryMethod.getKey().getParameterTypes()[0]));
				}
			}
		} catch (Exception e) {
			logger.error("inflection 赋值失败" + e.getLocalizedMessage());
		}
	}
	
	private static void setMethodMap(Object[] object) {
		/**
		 * boolean existsFlg=false; //先检查，新穿过来的参数方法，是否以前已经有了， if(methodMap!=null){ for (int i = 0; i < object.length;
		 * i++) { existsFlg=false; // Method[] method = object.getClass().getMethods();
		 * 
		 * //得到以前methodMap里面保存的所有object。 for(Object obj:methodMap.values()){ if(obj!=object[i]){ existsFlg=true;
		 * //如果以前存在这个object,就跳出循环 break; } } if(!existsFlg) break;//如果存在一个没有的，就得跳出整个循环，执行put
		 * 
		 * } } if(!existsFlg){ methodMap = new HashMap<Method, Object>(); for (int i = 0; i < object.length; i++) { for
		 * (Method method : object[i].getClass().getMethods()) { methodMap.put(method, object[i]); } } }
		 **/
		boolean findFlag = false;
		if (methodMap != null) {
			for (int i = 0; i < object.length; i++) {
				findFlag = false;
				for (Object o : methodMap.values()) {
					if (o.equals(object[i])) {
						findFlag = true;
						break;
					}
				}
				if (!findFlag)
					break;
			}
		}
		if (!findFlag) {
			methodMap = new HashMap<Method, Object>();
			for (int i = 0; i < object.length; i++) {
				for (Method method : object[i].getClass().getMethods()) {
					methodMap.put(method, object[i]);
				}
			}
		}
		
	}
	
	private static Object ConvertType(Object obj, Class clazz) {
		if (obj == null)
			return null;
		Object result = obj;
		String className = clazz.getName();
		if (!obj.getClass().getName().equals(className)) {
			try {
				if (className.equals("java.lang.String")) {
					if (obj.getClass().getName().equals("java.util.Date")) {
						result = new SimpleDateFormat(dateFormat).format(obj);
					} else {
						result = String.valueOf(obj);
					}
				} else if (className.equals("java.lang.Integer") || className.equals("int")) {
					result = Integer.parseInt(obj.toString());
				} else if (className.equals("java.lang.Float") || className.equals("float")) {
					result = Float.parseFloat(obj.toString());
				} else if (className.equals("java.lang.Double") || className.equals("double")) {
					result = Double.parseDouble(obj.toString());
				} else if (className.equals("java.util.Date")) {
					result = new SimpleDateFormat(dateFormat).parse(obj.toString());
				}
			} catch (Exception e) {
				logger.error("参数类型错误！");
				obj = null;
			}
		}
		return result;
	}
	
	public static void main(String[] args) {
		// XmlFactoryTest();
		// ApplicationContextTest();
	}
	
	/**
	 * applicationContext详解，执行之后加载全部bean对象，可以实现对xml检查错误
	 * BeanFactroy采用的是延迟加载形式来注入Bean的，即只有在使用到某个Bean时(调用getBean())，才对该Bean进行加载实例化， 这样，我们就不能发现一些存在的Spring的配置问题。
	 * 而ApplicationContext则相反，它是在容器启动时，一次性创建了所有的Bean。 这样，在容器启动时，我们就可以发现Spring中存在的配置错误。
	 */
	private static void ApplicationContextTest() {
		String classPath = "reflact.xml";
		ApplicationContext ac = new ClassPathXmlApplicationContext(classPath);
		
	}
	
	/**
	 * XmlBeanFactory详解,可以延迟加载，当执行getBean("**")才能加载某个类的对象，不能对整个xml内容执行检查
	 */
	private static void XmlFactoryTest() {
		String classPath = "reflact.xml";
		String filePath = "D:/java/workspace/demoss/StudyTest/WebRoot/WEB-INF/classes/reflact.xml";
		// resource是接口，classPathResource和filePathResource 分别是实现类，一个是相对地址，一个是具体的地址
		Resource classPathResource = new ClassPathResource(classPath);
		Resource filePathResource = new FileSystemResource(filePath);
		
		// 根据resource得到xmlBeanFactory IOC容器，spring 最核心的接口，是spring框架的基础设施，面向spring本身
		XmlBeanFactory xmlBean = new XmlBeanFactory(classPathResource);
		XmlBeanFactory xmlBean2 = new XmlBeanFactory(filePathResource);
		
		// XmlBean 在第一调用的时候才会被加载，不然不会加载
		
		// MainTest mainTest=(MainTest) xmlBean.getBean("reflact");
		// MainTest mainTest2=(MainTest) xmlBean2.getBean("reflact");
		
		// System.out.println(mainTest.debug);
		// System.out.println(mainTest2.debug);
		
		// 如果bean是单例的话BeanFactory会缓存bean的示例, 测试的时候可以去掉这个singleton="prototype",如果不填写默认是单例的。
		// <bean id="reflact" class="com.ioctest.service.MainTest" scope="singleton">
		
		// MainTest mainTest3=(MainTest) xmlBean.getBean("reflact");
		// System.out.println(mainTest==mainTest3);
		
	}
	
	/**
	 * spring设计了一个Resource接口，用来访问底层资源的能力。
	 * Resource有以下实现类：ClassPathResource,FileSystemResource，类里面方法exists(),isOpen()，getFile();
	 * ApplicationContext接口由BeanFactory派生而来的，有以下实现类：ClassPathXmlApplicationContext和FileSystemXmlApplicationContext
	 */
	
	public Map<Method, Object> getMethodMap() {
		return methodMap;
	}
	
	public void setMethodMap(Map<Method, Object> methodMap) {
		this.methodMap = methodMap;
	}
	
	public Map<String, String> getDefalutMap() {
		return defalutMap;
	}
	
	public void setDefalutMap(Map<String, String> defalutMap) {
		this.defalutMap = defalutMap;
	}
	
	public boolean isDebug() {
		return debug;
	}
	
	public void setDebug(boolean debug) {
		this.debug = debug;
	}
	
	public String getDateFormat() {
		return dateFormat;
	}
	
	public void setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}
	
}
