function pageChange(pageNum,pages){
    if(pageNum > 0 && pageNum <= pages){
        window.location.href = "/Library/manageborrow?pageNum=" + pageNum;
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
    if (confirm("确认删除吗")){
        window.location.href = "/Library/deleteborrow?borrowdate=" + borrowdate;
    }
}