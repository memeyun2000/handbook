import schedule
import time
import threading
import queue

from tools import ConnectTool

# 计算任务方法
def work():
    if not __queue.empty():
        __task = __queue.get()
        print("执行 {}".format(__task))
    else:
        # 没有任务需要计算 休息一会
        print("task queue is empty,waiting...")
        # time.sleep(SLEEP_SECONDS)

# 生成任务 方法
def generate_work():
    if __queue.empty():
        print("generate work")
        __queue.put("一个任务")
    else:
        pass
        # 任务队列是满的 休息一会
        # time.sleep(SLEEP_SECONDS)
        
# 使用线程 执行任务
def run_thread(func):
    job_thread = threading.Thread(target = func)
    job_thread.start()

"""
初始化
"""
# 休息时间
SLEEP_SECONDS = 300
# 计算线程数量
WORK_THREAD_NUM = 3
# 计算线程，任务执行间隔
THREAD_SEPARATE = 2
# 生成任务线程，执行间隔
GENERATE_THREAD_SEPARATE = 10
# 退出标志
exit_flag = False
# 任务队列
__queue = queue.Queue()
    
    

""" 
开始
"""
if __name__ == "__main__":
    # 获取数据库链接
    # conn = ConnectTool.get_conn("resources/sqlite3.db")


    for i in range(WORK_THREAD_NUM):
        schedule.every(THREAD_SEPARATE).seconds.do(run_thread,work)

    # generate task method
    schedule.every(GENERATE_THREAD_SEPARATE).seconds.do(run_thread,generate_work)

    while not exit_flag:
        schedule.run_pending()
        time.sleep(1)

    # 善后
    # conn.close()
