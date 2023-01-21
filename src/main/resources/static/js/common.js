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
            dataType: "json",
          })
      )
    })
    await Promise.allSettled(tasks)
    location.reload()
  })

})
