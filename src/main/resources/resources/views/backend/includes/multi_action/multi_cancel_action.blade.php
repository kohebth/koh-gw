@can("delete_{$moduleName}")
    <button class='btn btn-danger' type="button" wire:click="updateMultiple('cancel')">
        <i class="fas fa-ban"></i> {{__("Cancel")}}
    </button>
@endcan
