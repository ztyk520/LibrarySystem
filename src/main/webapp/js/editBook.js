function editBook(id){
    var bookname = document.getElementById("input1").value;
    var author = document.getElementById("input2").value;
    var publisher = document.getElementById("input3").value;
    var ISBN = document.getElementById("input4").value;
    var count = document.getElementById("input5").value;
    if (id == "" || bookname == "" || author == "" || publisher == "" || ISBN == "" || count == ""){
        alert("数据有误")
    }else {
        window.location.href = "/Library/updateBook?id=" + id + "&bookname=" + bookname + "&author=" + author + "&publisher=" + publisher + "&ISBN=" + ISBN + "&count=" + count;
    }
}