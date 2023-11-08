//package com.zzh.juc.reflector;
//
//import java.sql.CallableStatement;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//public interface TypeHandler<T> {
//
//  /** 通过 PreparedStatement 为 SQL语句 绑定参数时，将数据从 Java类型 转换为 JDBC类型 */
//  void setParameter(PreparedStatement ps, int i, T parameter, JdbcType jdbcType) throws SQLException;
//
//  /** 从结果集获取数据时，将数据由 JDBC类型 转换成 Java类型 */
//  T getResult(ResultSet rs, String columnName) throws SQLException;
//
//  T getResult(ResultSet rs, int columnIndex) throws SQLException;
//
//  T getResult(CallableStatement cs, int columnIndex) throws SQLException;
//
//}
//
///**
// * 可用于实现自定义的 TypeHandler
// */
//public abstract class BaseTypeHandler<T> extends TypeReference<T> implements TypeHandler<T> {
//
//  /**
//   * 只是处理了一些数据为空的特殊情况，非空数据的处理都交给子类去处理
//   */
//  @Override
//  public void setParameter(PreparedStatement ps, int i, T parameter, JdbcType jdbcType) throws SQLException {
//    if (parameter == null) {
//      if (jdbcType == null) {
//        throw new TypeException("JDBC requires that the JdbcType must be specified for all nullable parameters.");
//      }
//      try {
//        ps.setNull(i, jdbcType.TYPE_CODE);
//      } catch (SQLException e) {
//        throw new TypeException("Error setting null for parameter #" + i + " with JdbcType " + jdbcType + " . "
//              + "Try setting a different JdbcType for this parameter or a different jdbcTypeForNull configuration property. "
//              + "Cause: " + e, e);
//      }
//    } else {
//      try {
//        setNonNullParameter(ps, i, parameter, jdbcType);
//      } catch (Exception e) {
//        throw new TypeException("Error setting non null for parameter #" + i + " with JdbcType " + jdbcType + " . "
//              + "Try setting a different JdbcType for this parameter or a different configuration property. "
//              + "Cause: " + e, e);
//      }
//    }
//  }
//
//  @Override
//  public T getResult(ResultSet rs, String columnName) throws SQLException {
//    try {
//      return getNullableResult(rs, columnName);
//    } catch (Exception e) {
//      throw new ResultMapException("Error attempting to get column '" + columnName + "' from result set.  Cause: " + e, e);
//    }
//  }
//}
//
//
//public class IntegerTypeHandler extends BaseTypeHandler<Integer> {
//
//  /**
//   * NonNull 就是 NoneNull，非空的意思
//   */
//  @Override
//  public void setNonNullParameter(PreparedStatement ps, int i, Integer parameter, JdbcType jdbcType)
//      throws SQLException {
//    // IntegerTypeHandler 就调用 PreparedStatement 的 setInt()方法
//    // BooleanTypeHandler 就调用 PreparedStatement 的 setBoolean()方法
//    // 其它的基本数据类型，以此类推
//    ps.setInt(i, parameter);
//  }
//
//  @Override
//  public Integer getNullableResult(ResultSet rs, String columnName)
//      throws SQLException {
//    int result = rs.getInt(columnName);
//    return result == 0 && rs.wasNull() ? null : result;
//  }
//
//  @Override
//  public Integer getNullableResult(ResultSet rs, int columnIndex)
//      throws SQLException {
//    int result = rs.getInt(columnIndex);
//    return result == 0 && rs.wasNull() ? null : result;
//  }
//
//  @Override
//  public Integer getNullableResult(CallableStatement cs, int columnIndex)
//      throws SQLException {
//    int result = cs.getInt(columnIndex);
//    return result == 0 && cs.wasNull() ? null : result;
//  }
//}
