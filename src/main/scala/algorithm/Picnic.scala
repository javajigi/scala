package algorithm

class Picnic(numberOfFriend: Int, allFriendPair: List[Tuple2[Int, Int]]) {
  def getCombinationCount(): Int = {
    val taken = Array.fill[Boolean](numberOfFriend)(false)
    getCombinationCount(taken)
  }

  def getCombinationCount(taken: Array[Boolean]): Int = {
    val firstFree = taken.indexOf(false)
    if (firstFree == -1) {
      1
    } else {
      var ret = 0

      ((firstFree + 1) until numberOfFriend).foreach(
        pairWith => {
          if (!taken(pairWith) && allFriendPair.contains((firstFree, pairWith))) {
            taken(firstFree) = true
            taken(pairWith) = true
            ret += getCombinationCount(taken)
            taken(firstFree) = false
            taken(pairWith) = false
          }
        }
      )

      ret
    }
  }
}
