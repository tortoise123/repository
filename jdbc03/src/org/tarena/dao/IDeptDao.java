package org.tarena.dao;

import java.sql.SQLException;
import java.util.List;

public interface IDeptDao {
	/**
	 * ��dept������Ϣд��DEPT��
	 * @param dept
	 * @throws SQLException
	 */
	public void add(Dept dept) throws SQLException;
	/**
	 * ����deptno���������ֶ�ֵ
	 * @param dept
	 * @throws SQLException
	 */
	public void update(Dept dept) throws SQLException;
	/**
	 * ����deptnoɾ��ĳһ�м�¼
	 * @param deptno
	 * @throws SQLException
	 */
	public void delete(Integer deptno) throws SQLException;
	/**
	 * ����deptno��ѯĳһ�м�¼
	 * @param deptno ���ű��
	 * @return ���Ŷ��� û�м�¼����null
	 * @throws SQLException
	 */
	public Dept findById(Integer deptno) throws SQLException;
	/**
	 * ��ѯDEPT���м�¼
	 * @return
	 * @throws SQLException
	 */
	public List<Dept> findAll() throws SQLException;
	
	/**
	 * ��ҳ��ѯ,��ѯָ����pageҳ�ļ�¼
	 * @param page �ڼ�ҳ
	 * @param pageSize һҳ��ʾ������
	 * @return ָ��ҳ�ļ�¼
	 * @throws SQLException
	 */
	public List<Dept> findPage(int page,int pageSize) throws SQLException;
	
	
	
}
