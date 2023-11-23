
function pageChange(pageNum,pages,bookname){
    if(pageNum > 0 && pageNum <= pages){
        if (bookname === null) {
            window.location.href = "/Library/userseacher?pageNum=" + pageNum;
        }else{
            window.location.href = "/Library/userseacher?pageNum=" + pageNum+"&bookname="+bookname;
        }
    }else if (pageNum > pages){
        alert("\u540e\u9762\u5df2\u7ecf\u6ca1\u6709\u4e86")
    }else{
        alert("\u5df2\u7ecf\u662f\u7b2c\u4e00\u9875\u4e86")
    }
}
function borrowBook(uid,bid){
    window.location.href = "/Library/usersearcher/borrow?uid=" + uid + "&bid=" + bid;
}
function exit(){
    window.location.href = "/Library/userexit";
}