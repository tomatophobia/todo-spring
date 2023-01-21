$(document).ready(function () {

  $(".js-check-all").click(function () {
    $("input.js-check").prop("checked", $(".js-check-all").prop("checked"));
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

})
