function exit(){
    window.location.href = "/Library/userexit";
}
function pageChange(pageNum,pages,bookname){
    if(pageNum > 0 && pageNum <= pages){
        if (bookname === null) {
            window.location.href = "/Library/userreturn?pageNum=" + pageNum;
        }else{
            window.location.href = "/Library/userreturn?pageNum=" + pageNum+"&bookname="+bookname;
        }
    }else if (pageNum > pages){
        alert("\u540e\u9762\u5df2\u7ecf\u6ca1\u6709\u4e86")
    }else{
        alert("\u5df2\u7ecf\u662f\u7b2c\u4e00\u9875\u4e86")
    }
}
function returnBook(uid,bid){
    window.location.href = "/Library/returnbook?uid="+uid+"&bid="+bid;
}