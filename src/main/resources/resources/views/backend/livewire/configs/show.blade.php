<div class="row mt-4">
    <div class="col">

        <div class="form-horizontal">

            <div class="form-group row">
                {{ html()->label('Name')->class('col-sm-2 form-control-label')->for('name') }}
                <div class="col-sm-10">
                    <input type="text" wire:model.defer="configs.name" class="form-control bg-white" readonly>
                </div>
            </div>

            <div class="form-group row">
                {{ html()->label('Value')->class('col-sm-2 form-control-label')->for('value') }}
                <div class="col-sm-10">
                    <textarea type="text" wire:model.defer="configs.value" rows="4" class="form-control bg-white" readonly></textarea>
                </div>
            </div>

            <div class="form-group row">
                {{ html()->label('Description')->class('col-sm-2 form-control-label')->for('description') }}
                <div class="col-sm-10">
                    <input type="text" wire:model.defer="configs.description" class="form-control bg-white" readonly>
                </div>
            </div>

            <div class="form-group row">
                {{ html()->label('Status')->class('col-6 col-sm-2 form-control-label')->for('status') }}
                <div class="col-6 col-sm-10">
                    <input type="checkbox" wire:model.defer="configs.status" disabled class="form-control bg-white" style="width: 40px">
                </div>
            </div>

            <div class="row">
                <div class="col-12">
                    <div class="form-group">
                        <x-buttons.cancel> {{__('Back')}}</x-buttons.cancel>
                    </div>
                </div>
            </div>
        </div>

    </div>
</div>
