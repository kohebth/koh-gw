<div id="confirm-modal" class="modal" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">{{__("Confirmation")}}</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <p>{{__("Are you sure?")}}</p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">{{__("Close")}}</button>
                <button type="submit" class="btn btn-primary">{{__("Confirm")}}</button>
            </div>
        </div>
    </div>
</div>
@push('after-scripts')
    <script type="text/javascript">
        $("a[data-delete]").click(function(ev) {
            ev.preventDefault();

        });
    </script>
@endpush
