"""日期方法类
"""
import datetime
from datetime import timedelta


def add_days(__datetime, n):
    """日期 + 天
    """
    return __datetime + timedelta(days=n)

def  add_months(datetime1, n = 1):
    """日期 + 月
    """
    # create a shortcut object for one day
    one_day = datetime.timedelta(days = 1)
 
    # first use div and mod to determine year cycle
    q,r = divmod(datetime1.month + n, 12)
 
    # create a datetime2
    # to be the last day of the target month
    datetime2 = datetime.datetime(
        datetime1.year + q, r + 1, 1) - one_day
 
    if datetime1.month != (datetime1 + one_day).month:
        return datetime2

    if datetime1.day >= datetime2.day:
        return datetime2
 
    return datetime2.replace(day = datetime1.day)

def add_weeks(__datetime,n):
    """日期 + 周
    """
    return __datetime + timedelta(weeks=n)

def add_years(__datetime,n):
    """日期 + 年
    """
    return __datetime.replace(year = __datetime.year + n)

if __name__ == "__main__":
    now = datetime.date.today()
    print(now)
    # add day
    print(add_days(now, -1))
    # add month
    print(add_months(now, -1))
    # add week
    print(add_weeks(now,-1))
    # add year
    print(add_years(now,-1))
