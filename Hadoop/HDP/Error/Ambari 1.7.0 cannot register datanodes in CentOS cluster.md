
Stack Exchange Inbox Reputation and Badges
sign up log in tour help


Stack Overflow
  ● Questions

  ● Jobs

  ● Documentation

  ● Tags

  ● Users

  ● Badges

  ● Ask Question
Dismiss
Announcing Stack Overflow Documentation
We started with Q&A. Technical documentation is next, and we need your help.
Whether you're a beginner or an experienced developer, you can contribute.
Sign up and start helping → Learn more about Documentation →
Ambari 1.7.0 cannot register datanodes in CentOS cluster


up vote0down votefavoriteHere is another question about getting hosts to register. I am using Ambari 1.7.0 on CentOS 6 machines. I am trying to install HDP 2.1.
First here is the hosts file I am using. Note each node has the same hosts file:
127.0.0.1 localhost localhost.localdomain localhost4 localhost4.localdomain4 ::1 localhost localhost.localdomain localhost6 localhost6.localdomain6 192.168.200.144 datanode10.localdomain.com 192.168.200.107 datanode01.localdomain.com 192.168.200.143 namenode.localdomain.com
Also, I can ping each machine for any machine. I can SSH without a password from the name node into the other datanodes. I disabled selinux and iptables on all machines.
I am following the startup procedure listed here:https://cwiki.apache.org/confluence/display/AMBARI/Install+Ambari+1.7.0+from+Public+Repositories. Please note that these install instructions mention nothing about iptables or selinux. People on the mailing list have told me that I need to disable those items.
Ambari can discover the namenode it is sitting on. It cannot discover the datanodes. I get this error from the registration log file:
Verifying ambari-agent process status... Ambari Agent successfully started Agent PID at: /var/run/ambari-agent/ambari-agent.pid Agent out at: /var/log/ambari-agent/ambari-agent.out Agent log at: /var/log/ambari-agent/ambari-agent.log ("WARNING 2014-12-17 10:43:08,349 NetUtil.py:92 - Server at https://namenode.localdomain.com.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode:8440 is not reachable, sleeping for 10 seconds...
Why is the namenode being appended to the namenode.localdomain.com URL? Why is the script considering this a valid URL and not throwing an error?
What follows is the full registration log file.
========================== Creating target directory... ========================== Command start time 2014-12-17 10:43:22 Connection to datanode10.localdomain.com closed. SSH command execution finished host=datanode10.localdomain.com, exitcode=0 Command end time 2014-12-17 10:43:22 ========================== Copying common functions script... ========================== Command start time 2014-12-17 10:43:22 scp /usr/lib/python2.6/site-packages/ambari_commons host=datanode10.localdomain.com, exitcode=0 Command end time 2014-12-17 10:43:23 ========================== Copying OS type check script... ========================== Command start time 2014-12-17 10:43:23 scp /usr/lib/python2.6/site-packages/ambari_server/os_check_type.py host=datanode10.localdomain.com, exitcode=0 Command end time 2014-12-17 10:43:23 ========================== Running OS type check... ========================== Command start time 2014-12-17 10:43:23 Cluster primary/cluster OS type is redhat6 and local/current OS type is redhat6 Connection to datanode10.localdomain.com closed. SSH command execution finished host=datanode10.localdomain.com, exitcode=0 Command end time 2014-12-17 10:43:23 ========================== Checking 'sudo' package on remote host... ========================== Command start time 2014-12-17 10:43:23 sudo-1.8.6p3-15.el6.x86_64 Connection to datanode10.localdomain.com closed. SSH command execution finished host=datanode10.localdomain.com, exitcode=0 Command end time 2014-12-17 10:43:24 ========================== Copying repo file to 'tmp' folder... ========================== Command start time 2014-12-17 10:43:24 scp /etc/yum.repos.d/ambari.repo host=datanode10.localdomain.com, exitcode=0 Command end time 2014-12-17 10:43:24 ========================== Moving file to repo dir... ========================== Command start time 2014-12-17 10:43:24 Connection to datanode10.localdomain.com closed. SSH command execution finished host=datanode10.localdomain.com, exitcode=0 Command end time 2014-12-17 10:43:24 ========================== Copying setup script file... ========================== Command start time 2014-12-17 10:43:24 scp /usr/lib/python2.6/site-packages/ambari_server/setupAgent.py host=datanode10.localdomain.com, exitcode=0 Command end time 2014-12-17 10:43:24 ========================== Running setup agent script... ========================== Command start time 2014-12-17 10:43:24 Verifying Python version compatibility... Using python /usr/bin/python2.6 Found ambari-agent PID: 3622 Stopping ambari-agent Removing PID file at /var/run/ambari-agent/ambari-agent.pid ambari-agent successfully stopped Restarting ambari-agent Verifying Python version compatibility... Using python /usr/bin/python2.6 ambari-agent is not running. No PID found at /var/run/ambari-agent/ambari-agent.pid Verifying Python version compatibility... Using python /usr/bin/python2.6 Checking for previously running Ambari Agent... Starting ambari-agent Verifying ambari-agent process status... Ambari Agent successfully started Agent PID at: /var/run/ambari-agent/ambari-agent.pid Agent out at: /var/log/ambari-agent/ambari-agent.out Agent log at: /var/log/ambari-agent/ambari-agent.log ("WARNING 2014-12-17 10:43:08,349 NetUtil.py:92 - Server at https://namenode.localdomain.com.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode:8440 is not reachable, sleeping for 10 seconds... INFO 2014-12-17 10:43:18,359 NetUtil.py:48 - Connecting to https://namenode.localdomain.com.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode:8440/ca WARNING 2014-12-17 10:43:18,360 NetUtil.py:71 - Failed to connect to https://namenode.localdomain.com.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode:8440/ca due to [Errno -2] Name or service not known WARNING 2014-12-17 10:43:18,360 NetUtil.py:92 - Server at https://namenode.localdomain.com.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode:8440 is not reachable, sleeping for 10 seconds... INFO 2014-12-17 10:43:28,370 NetUtil.py:48 - Connecting to https://namenode.localdomain.com.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode:8440/ca WARNING 2014-12-17 10:43:28,370 NetUtil.py:71 - Failed to connect to https://namenode.localdomain.com.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode:8440/ca due to [Errno -2] Name or service not known WARNING 2014-12-17 10:43:28,371 NetUtil.py:92 - Server at https://namenode.localdomain.com.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode:8440 is not reachable, sleeping for 10 seconds... INFO 2014-12-17 10:43:31,082 main.py:83 - loglevel=logging.INFO INFO 2014-12-17 10:43:31,082 main.py:55 - signal received, exiting. INFO 2014-12-17 10:43:31,082 ProcessHelper.py:39 - Removing pid file INFO 2014-12-17 10:43:31,083 ProcessHelper.py:46 - Removing temp files INFO 2014-12-17 10:43:36,764 main.py:83 - loglevel=logging.INFO INFO 2014-12-17 10:43:36,764 DataCleaner.py:36 - Data cleanup thread started INFO 2014-12-17 10:43:36,765 DataCleaner.py:117 - Data cleanup started INFO 2014-12-17 10:43:36,767 DataCleaner.py:119 - Data cleanup finished INFO 2014-12-17 10:43:36,801 PingPortListener.py:51 - Ping port listener started on port: 8670 WARNING 2014-12-17 10:43:36,802 main.py:235 - Unable to determine the IP address of the Ambari server 'namenode.localdomain.com.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode' INFO 2014-12-17 10:43:36,802 NetUtil.py:48 - Connecting to https://namenode.localdomain.com.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode:8440/ca WARNING 2014-12-17 10:43:36,802 NetUtil.py:71 - Failed to connect to https://namenode.localdomain.com.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode:8440/ca due to [Errno -2] Name or service not known WARNING 2014-12-17 10:43:36,802 NetUtil.py:92 - Server at https://namenode.localdomain.com.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode.namenode:8440 is not reachable, sleeping for 10 seconds... ", None) Connection to datanode10.localdomain.com closed. SSH command execution finished host=datanode10.localdomain.com, exitcode=0 Command end time 2014-12-17 10:43:39 Registering with the server... Registration with the server failed.
linux hadoop hortonworks-data-platformadd a comment
shareimprove this questionasked Dec 17 '14 at 17:20
Dave358

1 Answer
activeoldestvotes
up vote0down voteacceptedWhat do you have specified as the hostname in /etc/ambari-agent/conf/ambari-agent.ini ? I assume that it is 'namenode.localdomain.com'add a comment
shareimprove this answeranswered Dec 18 '14 at 0:38
John Speidel461

sorry, I know this isn't an answer but when I tried to add a comment to the original question I got a message that I needed I higher reputation. – John Speidel Dec 18 '14 at 1:05I had to go the the other nodes in the cluster and check the ambari-agent.ini files there. They had the incorrect URL. They were using namenode.namenode.localdomain.com. – Dave Feb 4 '15 at 16:10

1
Your Answer

By posting your answer, you agree to the privacy policy and terms of service.
Not the answer you're looking for? Browse other questions tagged linux hadoop hortonworks-data-platform or ask your own question.
asked1 year agoviewed2065 timesactive1 year ago
BLOG
--------------------------------------------------------------------------------
Podcast #72 — Jay Doesn’t Get a Raise in This Podcast


Looking for a job?
  ● Java Chief Software Architect - $100k
CrossoverNo office location
REMOTE
hadoopjava
  ● Java Software Architect - $60k
CrossoverNo office location
REMOTE
hadoopjava
  ● Chief Java Architect - $100k
CrossoverNo office location
REMOTE
hadoopjava
  ● Full Stack Software Developer - BookingSuite
Booking.comBeijing, China
linuxpython
Related
0
What is the maximum number of datanodes in Hadoop cluster?
1
Docker-based Ambari 1.7 cluster install wizard repo URL dead (404) while 'Running setup agent script'
2
Ambari, connection failed on datanode
2
Ambari 2.0 setup in centos 5
-1
selecting public repo in ambari cluster install wizard
0
Correct steps to setup Ambari on a centos VM
0
How to start Datanode? (Cannot find start-dfs.sh script)
0
Apache Ambari : Datanode installation failed while installing in existing cluster
1
Unable to deploy HDP via Ambari 2.2.1.1
Hot Network Questions
  ● Is there such a thing as "too Orthodox"?
  ● Name of a humorous short story where man gets 3 wishes, but enemy gets double that wish?
  ● I don't want to live on this planet anymore
  ● \boxed border color
  ● How does an attacker get access to hashed passwords?
more hot questions
question feed
about us tour help blog chat data legal privacy policy work here advertising info mobile contact us feedback
TECHNOLOGYLIFE / ARTSCULTURE / RECREATIONSCIENCEOTHERStack Overflow
Server Fault
Super User
Web Applications
Ask Ubuntu
Webmasters
Game Development
TeX - LaTeXProgrammers
Unix & Linux
Ask Different (Apple)
WordPress Development
Geographic Information Systems
Electrical Engineering
Android Enthusiasts
Information SecurityDatabase Administrators
Drupal Answers
SharePoint
User Experience
Mathematica
Salesforce
ExpressionEngine® Answers
more (13)Photography
Science Fiction & Fantasy
Graphic Design
Movies & TV
Seasoned Advice (cooking)
Home Improvement
Personal Finance & Money
Academia
more (9)English Language & Usage
Skeptics
Mi Yodeya (Judaism)
Travel
Christianity
Arqade (gaming)
Bicycles
Role-playing Games
more (21)Mathematics
Cross Validated (stats)
Theoretical Computer Science
Physics
MathOverflow
Chemistry
Biology
more (5)Stack Apps
Meta Stack Exchange
Area 51
Stack Overflow Careers
site design / logo © 2016 Stack Exchange Inc; user contributions licensed under cc by-sa 3.0 with attribution required
rev 2016.8.24.3921
Stack Overflow requires external JavaScript from another domain, which is blocked or failed to load.

来源： http://stackoverflow.com/questions/27531215/ambari-1-7-0-cannot-register-datanodes-in-centos-cluster
