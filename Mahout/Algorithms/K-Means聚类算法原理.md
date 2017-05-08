一，K-Means聚类算法原理
       k-means 算法接受参数 k ；然后将事先输入的n个数据对象划分为 k个聚类以便使得所获得的聚类满足：同一聚类中的对象相似度较高；而不同聚类中的对象相似度较小。聚类相似度是利用各聚类中对象的均值所获得一个“中心对象”（引力中心）来进行计算的。
　　K-means算法是最为经典的基于划分的聚类方法，是十大经典数据挖掘算法之一。K-means算法的基本思想是：以空间中k个点为中心进行聚类，对最靠近他们的对象归类。通过迭代的方法，逐次更新各聚类中心的值，直至得到最好的聚类结果。
　　假设要把样本集分为c个类别，算法描述如下：
　　（1）适当选择c个类的初始中心；
　　（2）在第k次迭代中，对任意一个样本，求其到c个中心的距离，将该样本归到距离最短的中心所在的类；
　　（3）利用均值等方法更新该类的中心值；
　　（4）对于所有的c个聚类中心，如果利用（2）（3）的迭代法更新后，值保持不变，则迭代结束，否则继续迭代。
　　该算法的最大优势在于简洁和快速。算法的关键在于初始中心的选择和距离公式。
  二，mahout Kmeans聚类实现：
(1)参数input指定待聚类的所有数据点，clusters指定初始聚类中心
如果指定参数k，由org.apache.mahout.clustering.kmeans.RandomSeedGenerator.buildRandom
通过org.apache.hadoop.fs直接从input指定文件中随机读取k个点放入clusters中

(2)根据原数据点和上一次迭代(或初始聚类)的聚类中心计算本次迭代
的聚类中心，输出到clusters-N目录下。
该过程由org.apache.mahout.clustering.kmeans下的
KMeansMapper\KMeansCombiner\KMeansReducer\KMeansDriver实现

KMeansMapper：在configure中初始化mapper时读入上一次迭代产生或初始聚类中心
(每个mapper都读入所有的聚类中心);
map方法对输入的每个点，计算距离其最近的类，并加入其中
输出key为该点所属聚类ID，value为KMeansInfo实例，包含点的个数和各分量的累加和

KMeansCombiner：本地累加KMeansMapper输出的同一聚类ID下的点个数和各分量的和

KMeansReducer：累加同一聚类ID下的点个数和各分量的和，求本次迭代的聚类中心；
并根据输入Delta判断该聚类是否已收敛：上一次迭代聚类中心与本次迭代聚类中心距离<Delta；
输出各聚类中心和其是否收敛标记

KMeansDriver：控制迭代过程直至超过最大迭代次数或所有聚类都已收敛
每轮迭代后，KMeansDriver读取其clusters-N目录下的所有聚类，若所有聚类已收敛
则整个Kmeans聚类过程收敛了。



参数调整 ：

manhout Kmeans聚类有两个重要参数：收敛Delta和最大迭代次数

个人觉得Delta值越小，表示收敛条件越高，因此最终收敛的聚类数可能会降低，

而最大迭代次数可通过观察每次迭代后收敛聚类数决定，当收敛聚类数几乎不再变化或震荡时可停止迭代了

来源： <http://blog.csdn.net/yclzh0522/article/details/6839641>
