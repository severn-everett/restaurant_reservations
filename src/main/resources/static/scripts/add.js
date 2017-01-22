$(function () {
    var currentDate = new Date();
    $("#dateSpinner").datepicker({
        defaultDate: currentDate,
        minDate: currentDate,
        maxDate: "+1m",
        currentText: "Today",
        dateFormat: 'mm/dd/yy'
    });
    $("#dateSpinner").change(function() {
        var selectedDate = $("#dateSpinner").val();
        $.ajax({url: "/availability/hours", data: {date: selectedDate}, success: function(response) {
            var beginTime = response.beginHour > 12 ?
                    (response.beginHour - 12) + ":00 PM" :
                    response.beginHour + ":00 AM";
            var endTime = response.endHour > 12 ?
                    (response.endHour - 12) + ":00 PM" :
                    response.endHour + ":00 AM";
            $("#timeSpinner").timepicker({
                min: beginTime,
                max: endTime
            });
            var currentTimeSel = $("#timeSpinner").val();
            if (currentTimeSel != null) {
                currentTimeSel = Date.parse("January 1, 2000, " + currentTimeSel);
                var beginTimeDate = Date.parse("January 1, 2000, " + beginTime);
                var endTimeDate = Date.parse("January 1, 2000, " + endTime);
                if (currentTimeSel < beginTimeDate) {
                    $("#timeSpinner").val(beginTime);
                } else if (currentTimeSel >= endTimeDate) {
                    $("#timeSpinner").val(endTime);
                }
            } else {
                $("#timeSpinner").val(beginTime);
            }
        }});
    });
    $("#dateSpinner").val($.datepicker.formatDate('mm/dd/yy', currentDate));
    $.widget("ui.timepicker", $.ui.spinner, {
        options: {
            // seconds
            step: 60 * 1000 * 15,
            // hours
            page: 60
        },

        _parse: function( value ) {
            if ( typeof value === "string" ) {
                // already a timestamp
                if ( Number( value ) == value ) {
                    return Number( value );
                }
                return +Globalize.parseDate( value );
            }
            return value;
        },

        _format: function( value ) {
            return Globalize.format( new Date(value), "t" );
        }
    });

    $("#timeSpinner").timepicker();
    $("#timeSpinner").prop("readonly", true);

    $("#partySizeSpinner").spinner({
        min: 1,
        max: 8,
        step: 1
    });
    $("#partySizeSpinner").val("4");
    $("#partySizeSpinner").prop("readonly", true);

    $("#findTable").click(function() {
        var selectedDate = Date.parse($("#timeSpinner").val() + " " + $("#dateSpinner").val());
        $("#targetTime").val(selectedDate.toString("MM/dd/yyyy HH:mm"));
        $("#partySize").val($("#partySizeSpinner").val());
        $("#tableSearchForm").submit();
    });

    $("#dateSpinner").change();
});