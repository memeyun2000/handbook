
/**使用lambda 进行分组汇总**/
object HelloWorld{
  def main(args: Array[String]): Unit = {
    val data = Array(
      Map("start_dt" -> "2010-01-01","end_dt" -> "2010-03-21","jobcode" -> "0001"),
      Map("start_dt" -> "2011-05-23","end_dt" -> "2011-06-02","jobcode" -> "0001"),
      Map("start_dt" -> "2013-01-01","end_dt" -> "2014-12-21","jobcode" -> "0001"),
      Map("start_dt" -> "2010-04-16","end_dt" -> "2011-07-21","jobcode" -> "0002"),
      Map("start_dt" -> "2012-03-01","end_dt" -> "2015-03-21","jobcode" -> "0002"),
      Map("start_dt" -> "2010-04-01","end_dt" -> "2016-06-21","jobcode" -> "0003")
    )

    val dataGrouped = data
        .groupBy(g => g.getOrElse("jobcode",""))
        .map(e =>
          (
            e._1,
            e._2.minBy(e => e.getOrElse("start_dt","")).getOrElse("start_dt",""),
            e._2.maxBy(e => e.getOrElse("end_dt","")).getOrElse("end_dt","")
          )
        )
    dataGrouped.foreach(println)

  }
}
