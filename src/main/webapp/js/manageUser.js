function exit(){
    window.location.href = "/Library/userexit";
}
function pageChange(pageNum,pages,username){
    if(pageNum > 0 && pageNum <= pages){
        if (username === null) {
            window.location.href = "/Library/manageuser?pageNum=" + pageNum;
        }else{
            window.location.href = "/Library/manageuser?pageNum=" + pageNum+"&username="+username;
        }
    }else if (pageNum > pages){
        alert("\u540e\u9762\u5df2\u7ecf\u6ca1\u6709\u4e86")
    }else{
        alert("\u5df2\u7ecf\u662f\u7b2c\u4e00\u9875\u4e86")
    }
}
function editUser(id){
    window.location.href = "/Library/edituserdisplay?id="+id;
}
function deleteUser(id){
    if (confirm("是否删除")) {
        window.location.href = "/Library/deleteuser?id=" + id;
    }
}