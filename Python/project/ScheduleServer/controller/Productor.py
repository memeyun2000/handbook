"""生产类
构造应计算的任务 放入任务队列 __queue
"""
import sqlite3

def product_task(__queue):
    """队列中的任务 被 消耗完的时候生产者重新填入新的任务
    """
    conn = sqlite3.connect("/share/handbook/Python/project/ScheduleServer/resources/sqlite3.db")
    __cursor = conn.cursor()
    # step 1: 获取计算任务依赖所需的 数据
    __ready_cal_task_sql_str = """
    select taskid , tasktype ,stat_dt 
    from task_fact where status in ('2')
    """
    # 查询 准备计算的任务
    __cursor.execute(__ready_cal_task_sql_str)
    __ready_cal_task_list = __cursor.fetchall()
    # 获取任务依赖关系
    __depend_task_sql_str = """
    select taskid ,depend_taskid 
    from task_depend_task
    """
    __cursor.execute(__depend_task_sql_str)
    __depend_task_list = __cursor.fetchall()
    # 获取时间依赖关系
    __depend_task_time_sql_str = """
    select taskid from task_depend_time
    """
    __cursor.execute(__depend_task_time_sql_str)
    __depend_task_time_list = __cursor.fetchall()
    # step 2: 计算任务依赖



    # step 3: 善后
    __cursor.close()
    conn.close()


if __name__ == "__main__":
    print("hello world")
    import queue
    queue_test = queue.Queue()
    product_task(queue_test)
