package Power;


import java.util.List;

public interface empDao {

    /**
     *根据员工的id进行查询
     * @return
     */
    List<Emp> getDeptByPage(String idStr);


    List<Emp> getPage();
}
