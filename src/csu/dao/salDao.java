package csu.dao;

public interface salDao {
    //�޸�����
    int updateSal(Number id, String col, String data);

    Object[][] getEmpInfo(String sqlOrder);

    String[] getColName(String sql);

    int getTotalCount();
}