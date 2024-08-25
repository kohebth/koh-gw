$(function () {
    $('[data-toggle="tooltip"]').tooltip();

    $('#flash-overlay-modal').modal();

    function initSelect2(){
        $(".select2").each((_i, e) => {
            var $e = $(e);
            $e.select2({
                theme: "bootstrap",
                placeholder: "-- Select an option --",
                allowClear: true,
                dropdownParent: $e.parent()
            });
        })
    }

    window.addEventListener('clearMessage', (e) => {
        $('.alert').hide();
    })

    initSelect2();
    window.addEventListener('select2', (e) => {
        initSelect2()
    })

    $("input[check-all]").click(function () {
        if (this.checked) {
            $('tbody input:checkbox:not(:checked)').click();
            $("[updateMultiple]").removeClass("d-none");
            $('tbody > tr').addClass('tr-hovered');
        } else {
            $('tbody input:checkbox:checked').click();
            $("[updateMultiple]").addClass("d-none");
            $('tbody > tr').removeClass('tr-hovered');
        }
    });

    $(".widget-update-multiple .btn-close").click(function () {
        $('tbody input:checkbox:checked').click();
        $("[updateMultiple]").addClass("d-none");
        $("input[check-all]:checked").click();
        $('tbody > tr').removeClass('tr-hovered');
    });

    $('tbody').on('click', 'tr', function (e) {
        if($(e.target).hasClass('btn') || $(e.target).hasClass('fas')) {
            return;
        }

        if (!$(e.target).hasClass('ignore-select') && $(e.target).closest('td').hasClass('ignore-select')) {
            return;
        }

        if(!$(e.target).is(':checkbox')) {
            $(this).find(':checkbox').click();
        }

        if ($(this).find(':checkbox:checked').length > 0) {
            $(this).addClass('tr-hovered');
        } else {
            $(this).removeClass('tr-hovered');
        }

        checkSameStatus();

        const count = $('tbody input:checkbox:checked').length;
        if (count > 0) {
            $('.selected-count').html(count);
            $("[updateMultiple]").removeClass("d-none");
        } else {
            $("[updateMultiple]").addClass("d-none");
        }

        if (count === $('tbody input:checkbox').length) {
            $('input[check-all]').prop('checked', true);
        } else {
            $('input[check-all]').prop('checked', false);
        }
    });

    function checkSameStatus(){
        let status = '';
        let isSameStatus = true;

        $('tbody input:checkbox:checked').each(function (){
            let thisStatus = $(this).parent().parent().find("td[data-status]").attr('data-status');
            if (status === '') {
                status = thisStatus;
            }

            if (status !== thisStatus) {
                isSameStatus = false;
                return false;
            }
        })

        if (isSameStatus === false) {
            $('.btn-multi').hide();
        } else {
            $('.btn-multi').show();

            if (status === 'active') {
                $('.btn-multi-active').hide();
            } else {
                $('.btn-multi-deactive').hide();
            }

            if (status === 'cancel') {
                $('.btn-multi-cancel').hide();
            } else {
                $('.btn-multi-draft').hide();
            }
        }
    }
})
