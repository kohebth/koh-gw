@can("delete_{$moduleName}")
    <button wire:click="updateMultiple('delete')" class='btn btn-danger' type="button"
            onclick="return confirm('Are you sure that you want to delete these records?') || event.stopImmediatePropagation()">
        <i class="fas fa-trash-alt"></i> {{__("Delete")}}
    </button>
@endcan
