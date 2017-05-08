> equals()

当满足下列条件时，表示两个Buffer相等：
  1. 有相同的类型（byte、char、int等）。
  2. Buffer中剩余的byte、char等的个数相等。
  3. Buffer中所有剩余的byte、char等都相同。
如你所见，equals只是比较Buffer的一部分，不是每一个在它里面的元素都比较。实际上，它只比较Buffer中的剩余元素。

> compareTo()方法

compareTo()方法比较两个Buffer的剩余元素(byte、char等)， 如果满足下列条件，则认为一个Buffer“小于”另一个Buffer：
  1. 第一个不相等的元素小于另一个Buffer中对应的元素 。
  2. 所有元素都相等，但第一个Buffer比另一个先耗尽(第一个Buffer的元素个数比另一个少)。
