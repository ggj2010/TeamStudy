package com.team.gaoguangjin.javabase.clon;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName:CloneableTest.java
 * @Description: 深拷贝和浅拷贝
 * @author gaoguangjin
 * @Date 2015-3-26 上午9:11:47
 */
@Slf4j
public class CloneableTest {
	static boolean isDeep;
	
	@Getter
	@Setter
	static class Father implements Cloneable {
		private String fatherName;
		private String fatherAge;
		private Child child;
		
		protected Father clone() throws CloneNotSupportedException {
			Father father = (Father) super.clone();
			if (isDeep)
				child = child.clone();// 两种深拷贝对象方法
			// father.child = new Child();// 这个是关键
			
			return father;
		}
		
		public String toString() {
			return fatherName + ":" + child.getChildName();
			
		}
	}
	
	@Getter
	@Setter
	static class Child implements Cloneable {
		private String childName;
		private String childAge;
		
		public String getChildName() {
			return childName;
		}
		
		public void setChildName(String childName) {
			this.childName = childName;
		}
		
		public String getChildAge() {
			return childAge;
		}
		
		public void setChildAge(String childAge) {
			this.childAge = childAge;
		}
		
		protected Child clone() throws CloneNotSupportedException {
			Child child = (Child) super.clone();
			return child;
		}
		
	}
	
	/**
	 * @Description: 浅拷贝：只复制一个对象，对象内部存在的指向其他对象数组或者引用则不复制 深拷贝：对象，对象内部的引用均复制
	 * @param args
	 * @throws CloneNotSupportedException
	 * @return:void
	 */
	public static void main(String[] args) throws CloneNotSupportedException {
		Father father = new Father();
		father.setFatherName("父亲");
		father.setChild(new Child());
		father.getChild().setChildName("孩子1");
		
		log.info("赋值之前：" + father.toString());
		
		// Father shallowCopy = father.clone();// 浅拷贝，
		// shallowCopy.getChild().setChildName("孩子2");
		// log.info("father的值：" + father.toString());
		// log.info("赋值之后，浅拷贝的child对象没拷贝，共享一个：" + shallowCopy.toString());
		
		isDeep = true;
		Father deepCopy = father.clone();// 深拷贝，将clone()方法注释的内容打开
		deepCopy.getChild().setChildName("孩子3");
		log.info("father赋值之后：" + father.toString());
		log.info("深拷贝赋值之后：" + deepCopy.toString());
		
	}
}
