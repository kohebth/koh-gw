@can("edit_{$moduleName}")
    <button wire:click="updateMultiple('active')" class='btn btn-success btn-multi btn-multi-active' type="button">
        <i class="fas fa-check"></i> {{__("Active")}}
    </button>
    <button wire:click="updateMultiple('deactive')" class='btn btn-warning btn-multi btn-multi-deactive' type="button">
        <i class="fas fa-ban"></i> {{__("Deactive")}}
    </button>
@endcan
