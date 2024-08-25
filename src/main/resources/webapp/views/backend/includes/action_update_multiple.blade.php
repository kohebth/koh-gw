<div class="widget-update-multiple form-group d-none" updateMultiple>
    <span class="selected-text"><span class="selected-count">0</span> row(s) selected.</span>
    @if (isset($actionBlock))
        @include("backend.includes.multi_action.multi_block_action")
    @endif
    @if(isset($actionDelete))
        @include("backend.includes.multi_action.multi_delete_action")
    @endif
    @if(isset($actionActive))
        @include("backend.includes.multi_action.multi_active_action")
    @endif
    @if(isset($actionCancel))
        @include("backend.includes.multi_action.multi_cancel_action")
    @endif
    @if(isset($actionCancelDraft))
        @include("backend.includes.multi_action.multi_cancel_draft_action")
    @endif
    @if(isset($actionSubmit))
        @include("backend.includes.multi_action.multi_submit_action")
    @endif
    @if(isset($actionResetAnnotationCount))
        @include("backend.includes.multi_action.multi_reset_annotation_count_action")
    @endif
    @if(isset($actionVerify))
        @include("backend.includes.multi_action.multi_verify_action")
    @endif
    <span class="btn-close">
        <i class="fas fa-times"></i>
    </span>
</div>
