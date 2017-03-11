package cf.study.codefight

import scala.collection.concurrent.TrieMap
import scala.collection.mutable
import scala.collection.mutable.ListBuffer
import scala.util.control.Breaks._

/**
  * Created by fan on 2017/3/2.
  */
class InterviewTests {
    /*
     * https://codefights.com/interview/EDaACHNYHyH6qQFAL
     */
    def wordLadder(beginWord: String, endWord: String, wordList: Array[String]): Unit = {
        val wordLen: Int = beginWord.length

        val _wordList: Array[String] = wordList :+ endWord
        val indexMaps: Array[TrieMap[Char, mutable.Buffer[String]]] = (0 until wordLen).toArray.map(TrieMap.empty)

        _wordList.foreach((word: String) => {
            word.zipWithIndex.foreach((c_i: (Char, Int)) => indexMaps(c_i._2).getOrElseUpdate(c_i._1, new ListBuffer[String]).append(word))
        })

        def countDiff(word1: String, word2: String): Int = word1.zip(word2).count((c2: (Char, Char)) => c2._1 != c2._2)

        var _word: String = beginWord
        while (_word != endWord) {
            breakable {
                for (i <- 0 until wordLen) {
                    if (_word(i) != endWord(i)) {

                    }
                }
            }
        }
    }
}
