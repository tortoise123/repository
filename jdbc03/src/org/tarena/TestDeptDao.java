package org.tarena;

import java.sql.SQLException;
import java.util.List;

import org.tarena.dao.Dept;
import org.tarena.dao.IDeptDao;
import org.tarena.dao.JdbcDeptDao;

public class TestDeptDao {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		testUpdate();
//		testFindById();
//		testFindAll();
		testFindPage();
	}
	
	public static void testFindPage(){
		IDeptDao dao = new JdbcDeptDao();
		try {
			//��ѯ��3ҳ�ļ�¼,ÿҳ�����ʾ����2��
			List<Dept> list = dao.findPage(3,2);
			for(Dept dept : list){
				System.out.println(dept.getDeptno()+" "
						+dept.getDname());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void testFindAll(){
		IDeptDao dao = new JdbcDeptDao();
		try {
			List<Dept> list = dao.findAll();
			for(Dept dept : list){
				System.out.println(dept.getDeptno()+" "
						+dept.getDname());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void testFindById(){
		IDeptDao dao = new JdbcDeptDao();
		try {
			Dept dept = dao.findById(70);
			if(dept != null){
				System.out.println(dept.getDname()+" "
					+dept.getLoc());
			}else{
				System.out.println("û�м�¼");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void testUpdate(){
		Dept dept = new Dept();
		dept.setLoc("����");
		dept.setDname("���Բ�");
		dept.setDeptno(60);
		IDeptDao dao = new JdbcDeptDao();
		try {
			dao.update(dept);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void testSave(){
		Dept dept = new Dept();
		dept.setDname("������");
		dept.setLoc("��������");
		IDeptDao dao = new JdbcDeptDao();
		try {
			dao.add(dept);
			System.out.println("��ӳɹ�");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("���ʧ��");
		}
	}
	
	

}
