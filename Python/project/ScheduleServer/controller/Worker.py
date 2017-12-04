"""消费者类
接收任务，并计算任务
"""

def work_route(taskfact):
    """
    任务路由
    """
    if taskfact.tasktype == "shell":
        print("execute shell task")
