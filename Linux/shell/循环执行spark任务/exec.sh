#!/bin/bash

#task list
TASK_LIST="PS_MSBD_HD_EMPL PS_MSBD_DEPT_TBL"

JAR_ARGS="--jar xxx.jar"

MEM_ARGS="--memory 4g"

if [ -z $1 ]; then
  STAT_DT="2016-03-01"
else
  STAT_DT="$1"
fi



#for exec task
for TASK in $TASK_LIST;
do
  echo "spark-submit ${MEM_ARGS} ${JAR_ARGS} ${TASK} ${STAT_DT}"
done
