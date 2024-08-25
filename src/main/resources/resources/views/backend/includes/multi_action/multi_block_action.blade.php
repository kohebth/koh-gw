@can("edit_{$moduleName}")
    <button wire:click="updateMultiple('block')" class='btn btn-warning' type="button">
        <i class="fas fa-ban"></i> {{__("Block")}}
    </button>
@endcan
