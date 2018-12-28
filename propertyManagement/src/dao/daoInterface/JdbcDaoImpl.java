package dao.daoInterface;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class JdbcDaoImpl<T> implements DAO<T> {
    private QueryRunner queryRunner = null;
    private Class<T> type;

    public JdbcDaoImpl(){
        queryRunner = new QueryRunner();
        Type genType = getClass().getGenericSuperclass();

        Class<T> superClassGenericParameterizedType = null;

        // 判断父类是否有泛型
        if (genType instanceof ParameterizedType) {
            // 向下转型，以便调用方法
            ParameterizedType pt = (ParameterizedType) genType;
            // 只取第一个，因为一个类只能继承一个父类
            Type superClazz = pt.getActualTypeArguments()[0];
            // 转换为Class类型
            superClassGenericParameterizedType = (Class<T>) superClazz;
        }

        type = superClassGenericParameterizedType;
    }

    //增删改操作
    @Override
    public void update(Connection connection, String sql, Object... args){
        try{
            queryRunner.update(connection,sql,args);
        } catch (SQLException e){
            e.printStackTrace();
        }

    }

    //一系列查询操作
    @Override
    public T get(Connection connection, String sql, Object... args){
        try {
            return queryRunner.query(connection, sql, new BeanHandler<>(type),args);
        } catch (SQLException e){
          e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<T> getList(Connection connection, String sql, Object... args){
        try {
            return queryRunner.query(connection, sql, new BeanListHandler<>(type), args);
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public <E> E getValue(Connection connection, String sql, Object... args) throws Exception {
        try{
            return (E) queryRunner.execute(connection, sql, new ScalarHandler(), args);
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public List<Map<String , Object>> getMap(Connection connection, String sql, Object... args){
        try{
            return queryRunner.query(connection, sql, new MapListHandler(), args);
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
