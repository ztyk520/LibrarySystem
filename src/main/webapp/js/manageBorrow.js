function pageChange(pageNum,pages,bid,bookname,uid){
    if(pageNum > 0 && pageNum <= pages){
        var path = "/Library/manageborrow?pageNum=" + pageNum;
        if(bid != null){
            path = path + "&bid="+bid;
        }
        if (bookname != null){
            path = path + "&bookname="+bookname;
        }
        if(uid != null){
            path = path + "&uid=" + uid;
        }
        window.location.href = path;
    }else if (pageNum > pages){
        alert("\u540e\u9762\u5df2\u7ecf\u6ca1\u6709\u4e86")
    }else{
        alert("\u5df2\u7ecf\u662f\u7b2c\u4e00\u9875\u4e86")
    }
}
function exit(){
    window.location.href = "/Library/userexit";
}
function deleteborrow(borrowdate){
    if (confirm("\u786e\u8ba4\u5220\u9664")){
        window.location.href = "/Library/deleteborrow?borrowdate=" + borrowdate;
    }
}