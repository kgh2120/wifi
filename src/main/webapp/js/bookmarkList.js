function fetchBookmarkList(){
  fetch(`/bookmark`)
  .then(res => res.json())
  .then(data => {
    let body = document.querySelector(".bookmark-table-body");
    data.forEach(datum => {
      let row = document.createElement("tr");
      row.innerHTML = `
      <td>${datum.id}</td>
      <td>${datum.group_name}</td>
      <td><a href="/view/wifiDetail.jsp?historyId=${datum.history_id}&wifiId=${datum.wifi_id}">${datum.wifi_name}</a></td>
      <td>${datum.created_at}</td>
      `
      let td = document.createElement("td");
      let span = document.createElement("span");
      span.innerText = "삭제";
      span.classList.add("anchor");
      span.addEventListener('click', () => onDeleteButtonClicked(datum.id));
      td.append(span);
      row.append(td);
      body.append(row);
    })

  })
}

const onDeleteButtonClicked = (id) => {
  fetch(`/bookmark?id=${id}`,{
    method : "DELETE"
  }).then(()=>window.location.reload())
  .catch(err => console.error(err));
}


function init(){
  fetchBookmarkList();
}

init();