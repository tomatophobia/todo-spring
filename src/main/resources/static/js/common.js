$(document).ready(function () {

  $(".js-check-all").click(function () {
    $("input.js-check").prop("checked", $(".js-check-all").prop("checked"));
  })

  $(".js-create-todo").click(async function () {
    const $content = $(this).closest(".js-modal-content").find(".js-input-content")
    const data = {
      content: $content.val()
    }
    await $.ajax({
      url: `/api/todos`,
      method: "POST",
      contentType: "application/json; charset=utf-8",
      data: JSON.stringify(data),
    })
    location.reload()
  })

  $(".js-delete-todo").click(async function () {
    const tasks = []
    $("input.js-check:checked").each(function () {
      tasks.push(
          $.ajax({
            url: `/api/todos/${$(this).val()}`,
            method: "DELETE",
          })
      )
    })
    await Promise.allSettled(tasks)
    location.reload()
  })

  $(".js-update-todo").click(async function () {
    const buttonText = $(this).text()
    if (buttonText !== "수정완료") {
      $(this).text("수정완료")
      const $tdContent = $(this).closest("tr").children("td:eq(1)")
      const before = $tdContent.text()
      const child = `<input type="text" class="form-control js-input-content" value="${before}"/>`
      $tdContent.text("").append(child)
    } else {
      const id = $(this).val()
      const $content = $(this).closest("tr").find(".js-input-content")
      const data = {
        content: $content.val()
      }
      await $.ajax({
        url: `/api/todos/${id}`,
        method: "PUT",
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify(data),
      })
      location.reload()
    }
  })

})
