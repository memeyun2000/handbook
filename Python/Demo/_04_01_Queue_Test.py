# 多线程访问queue测试
# 测试queue是否线程安全
import schedule
import queue
import threading
import time
import datetime
import logging

# 获取数据
def get_data(__queue):
    print("get_data")
    if not __queue.empty():
        logging.error(__queue.get())
    
# 插入数据
def put_data(__queue):
    print("put_data")
    new_time_str = datetime.datetime.now()
    for v in range(11):
        __queue.put("{} {}".format(new_time_str,v))

logging.basicConfig(level=logging.ERROR,
                    filename='resources/temp.log',
                    filemode='w')
if __name__ == "__main__":
    __queue = queue.Queue()

    logging.info("hello world")
    
    #一个线程 存放数据
    schedule.every(0.001).seconds.do(put_data,__queue)
    # 多个线程读取数据
    schedule.every(0.001).seconds.do(get_data,__queue)
    schedule.every(0.001).seconds.do(get_data,__queue)
    schedule.every(0.001).seconds.do(get_data,__queue)
    schedule.every(0.001).seconds.do(get_data,__queue)
    schedule.every(0.001).seconds.do(get_data,__queue)
    schedule.every(0.001).seconds.do(get_data,__queue)
    schedule.every(0.001).seconds.do(get_data,__queue)
    schedule.every(0.001).seconds.do(get_data,__queue)
    schedule.every(0.001).seconds.do(get_data,__queue)
    while True:
        schedule.run_pending()
        time.sleep(0.0001)
    
    # 最后在验证 取出的数据是否有重复的