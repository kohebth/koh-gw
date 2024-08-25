@can("edit_{$moduleName}")
    <button wire:click="updateMultiple('verify')" class='btn btn-success' type="button">
        <i class="fas fa-check"></i> {{__("Verify")}}
    </button>
    <button wire:click="updateMultiple('not_verify')" class='btn btn-warning' type="button">
        <i class="fas fa-ban"></i> {{__("Not verify")}}
    </button>
@endcan