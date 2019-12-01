var link = (window.location.href).split("/");
var id = link[link.length - 1]
const port = window.location.host

$(document).ready(function () {
    //Khi bàn phím được nhấn và thả ra thì sẽ chạy phương thức này
    $("#myForm").validate({
        rules: {
            subjectType: {
                required: true,
                maxlength: 50
            },
            formatType: {
                required: true,
                maxlength: 50
            },
            budgetCode: {
                maxlength: 50,
                digits: true
            },
            plannedExpense: {
                maxlength: 50,
                digits: true
            },
            plannedEndDate: {
                isAfterStartDate: true
            },
            plannedStartDate: {
                isCurrentDate: true
            },
            actualStartDate: {
                isActualStartDate: true
            },
            actualEndDate: {
                isAfterActualStartDate: true
            },
            note: {
                maxlength: 3000
            }
        },
        messages: {
            subjectType: "Subject Type cannot be blank and max length is 50",
            formatType: "Format Type cannot be blank and max length is 50",
            budgetCode: "Budget Code cannot be blank and must be a digits and max length is 50",
            plannedExpense: "Planned Expense cannot be blank and must be a digits and max length is 50",
            note: "Note required max length 3000 characters"
        },
        highlight: function (element) {
            $(element).addClass(
                'is-invalid border border-danger rounded');
        },
        unhighlight: function (element) {
            $(element).removeClass(
                'is-invalid border border-danger rounded');
        },

    });
    var isCurrentDate = function (current, startDatestr) {
        var inDate = new Date(current);
        var split = startDatestr.split("/");
        var startDate = split[1] + "/" + split[0] + "/" + split[2];
        var eDate = new Date(
            startDate);
        if (inDate <= eDate) {
            return true;
        }
        return false;

    };
    jQuery.validator
        .addMethod("isCurrentDate", function (value,
            element) {
            var today = new Date();
            var dd = String(today.getDate())
                .padStart(2, '0');
            var mm = String(today.getMonth() + 1)
                .padStart(2, '0'); //January is 0!
            var yyyy = today.getFullYear();
            today = mm + '/' + dd + '/' + yyyy;
            return isCurrentDate(today, $(
                '#plannedStartDate').val().toString());
        },
            "Start date must be larger than current date");

    var isAfterStartDate = function (startDateStr,
        endDateStr) {
        var split1 = startDateStr.split("/");
        var startDate = split1[1] + "/" + split1[0] + "/" + split1[2];
        var split2 = endDateStr.split("/");
        var endDate = split2[1] + "/" + split2[0] + "/" + split2[2];
        var inDate = new Date(startDate), eDate = new Date(
            endDate);
        if (inDate <= eDate) {
            return true;
        }
        return false;
    };
    jQuery.validator.addMethod("isAfterStartDate",
        function (value, element) {
            return isAfterStartDate($('#plannedStartDate').val().toString(), value);
        }, "End date must be larger than start date");

    var isActualStartDate = function (startDateStr,
        actualStartDate) {
        var split1 = startDateStr.split("/");
        var startDate = split1[1] + "/" + split1[0] + "/" + split1[2];
        var split2 = actualStartDate.split("/");
        var endDate = split2[1] + "/" + split2[0] + "/" + split2[2];
        var inDate = new Date(startDate), eDate = new Date(
            endDate);
        if (inDate <= eDate) {
            return true;
        }
        return false;
    };
    jQuery.validator.addMethod("isActualStartDate",
        function (value, element) {
            return isActualStartDate($('#plannedStartDate').val().toString(), value);
        }, "Actual Start Date must be larger than Planned Start Date");

    var isAfterActualStartDate = function (startDateStr,
        endDateStr) {
        var split1 = startDateStr.split("/");
        var startDate = split1[1] + "/" + split1[0] + "/" + split1[2];
        var split2 = endDateStr.split("/");
        var endDate = split2[1] + "/" + split2[0] + "/" + split2[2];
        var inDate = new Date(startDate), eDate = new Date(
            endDate);
        if (inDate <= eDate) {
            return true;
        }
        return false;
    };
    jQuery.validator.addMethod("isAfterActualStartDate",
        function (value, element) {
            return isAfterActualStartDate($('#actualStartDate').val().toString(), value);
        }, "Actual End Date must be larger than Actual Start Date");

});

function changeCourseCode() {
    var arrIdCourse = document.getElementById("idUnchange").value
        .split("_");
    var lastCourseCode = arrIdCourse[3] + "_" + arrIdCourse[4];
    var courseCode = document.getElementById("courseid");
    var arrSubSubjectType = document.getElementById("subSubjectType").value
        .split("_");
    var subSubjectType = arrSubSubjectType[1];
    var arrCourseName = document.getElementById("courseName").value
        .split("_");
    var courseName = arrCourseName[1];
    var arrSupplier = document.getElementById("supplier").value
        .split("_");
    var supplier = arrSupplier[1];

    courseCode.value = supplier + "_" + courseName + "_" +
        subSubjectType + "_" + lastCourseCode;
}

function showTable() {
    $(document).ready(function () {
        $.getJSON('http://' + port + '/api/eventDetail/' + id, function (data) {
            const idEvent = data.idEvent
            var url = 'http://' + port + '/api/showDetail/' + id
            var tableCounter = $('#candidateList').DataTable({
                stateSave: true,
                "aLengthMenu": [5, 10, 15, 100],
                "columnDefs": [
                    {
                        'targets': 0,
                        'checkboxes': {
                            'selectRow': true
                        }
                    },
                    {
                        "targets": 3,
                        "render": $.fn.dataTable.render.moment('DD/MM/YYYY')
                    }
                ],
                'select': {
                    'style': 'multi'
                },
                "ajax": {
                    url: url,
                    dataSrc: ''
                },
                "columns": [
                    { data: "idCandidate" },
                    {
                        data: "email",
                        mRender: function (data, type, row) {
                            return '<a href="/candidateDetail/' + row.idCandidate + '">' +
                                data +
                                '</a>'
                        }
                    },
                    { data: "name" },
                    { data: "dayOfBirth" },
                    { data: "gender" },
                    { data: "phone" },
                    { data: "canStatus" }
                ]
            })

            $('#select').on('change', function () {
                var selected = tableCounter.column().checkboxes.selected();
                var arr = []
                var option = document.getElementById("select");
                var choose = option.options[option.selectedIndex].value;
                // Iterate over all selected checkboxes
                $.each(selected, function (index, rowId) {
                    arr.push(rowId)
                });
                let id = arr
                let status = choose
                const data = {
                    id,
                    status,
                    idEvent
                };
                fetch('http://' + port + '/api/changeStatus', {
                    method: 'POST',
                    credentials: 'include',
                    mode: 'cors', // no-cors, cors, *same-origin
                    cache: 'no-cache',
                    redirect: 'follow', // manual, *follow, error
                    referrer: 'no-referrer', // no-referrer, *client
                    headers: {
                        'Content-Type': 'application/json',
                    },
                    body: JSON.stringify(data),
                })
                    .then((json) => {
                        console.log(json)
                        tableCounter.ajax.reload(null, false); // user paging is not reset on reload
                    });
            })
        })
    })
}

