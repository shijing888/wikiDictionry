# wikiDictionry
wikiDictionary，利用wikipedia创建的词典，可用于消歧、问答等。
1、首先下载最近离线版wikitext利用jwpl包构造数据库，具体操作如下：
    http://www.cnblogs.com/CherishFX/p/5280259.html
    
2、利用已构造好的数据库来生成基于wikipedia的同义歧义词典，具体代码见GenerateDictionary类，
    其中同义词典基于词条title、重定向页及页内锚文字构建；歧义词典基于消歧页、页内锚文字构建。
    使用中若发现内存溢出，可以考虑将方法剥离，先后构造同义与歧义词典。

3、利用生成的词典进行实体消歧
    待完成。。
