@can("edit_{$moduleName}")
    <button class='btn btn-warning btn-multi btn-multi-cancel' type="button" wire:click="updateMultiple('cancel')">
        <i class="fas fa-ban"></i> {{__("Cancel")}}
    </button>
    <button class='btn btn-success btn-multi btn-multi-draft' type="button" wire:click="updateMultiple('draft')">
        <i class="fas fa-check"></i> {{__("Draft")}}
    </button>
@endcan
