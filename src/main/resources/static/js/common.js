$(document).ready(() => {
  $(".check-all").click(() => {
    $("input.check").prop("checked",$(".check-all").prop("checked"));
  })
})
