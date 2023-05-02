
function retrieveBookmarkGroupList(){
  fetch("/bookmark-group")
  .then(res => res.json())
  .then(data => {
    let body = document.querySelector(".wifi-body");
    data.forEach(d => {
      let tr = document.createElement("tr");
      let keys = Object.keys(d);
      tr.classList.add("table-row");
      tr.classList.add("bookmark-group-table-data");
      for (let i = 0; i<keys.length;i++) {
        let td = document.createElement("td");
        td.innerText = d[keys[i]];
        tr.append(td);
      }
      let td = document.createElement("td");
      let updateAnchor = document.createElement("a");
      updateAnchor.href = `/view/bookmarkPost.jsp?mode=edit&id=${d.id}&name=${d.name}&order=${d.orders}`;
      updateAnchor.innerText = "수정";

      let deleteAnchor = document.createElement("span");
      deleteAnchor.addEventListener('click', () =>deleteButtonClicked(d.id));
      deleteAnchor.innerText = "삭제";
      deleteAnchor.classList.add("anchor");
      td.append(updateAnchor);
      td.append(deleteAnchor);

      tr.append(td);
      body.append(tr);
    })
  })
}
function deleteButtonClicked(id){

  fetch(`/bookmark-group?id=${id}`,{
    method : "delete"
  }).then(() => {
    window.location.reload()
  })
}

function moveToAddBookmarkGroup(){
  let addBtn = document.querySelector(".bookmark-add-btn");
  addBtn.addEventListener('click',moveToPostPageModeAdd)
}
function moveToPostPageModeAdd(){
  window.location.replace("/view/bookmarkPost.jsp?mode=add")
}

function init(){
  retrieveBookmarkGroupList();
  moveToAddBookmarkGroup();

}

init();